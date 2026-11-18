package com.veroanggra.furnitureshop.ui.organisms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.RoundRect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.PathOperation
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.veroanggra.furnitureshop.data.model.Product
import com.veroanggra.furnitureshop.ui.atoms.CartPill
import com.veroanggra.furnitureshop.ui.atoms.PriceText
import com.veroanggra.reactify.list.GooeySource
import com.veroanggra.reactify.list.GooeyTransferState

// Matches the proportions measured from the Figma source (Card Background top offset / card width).
private const val ImageOverflowFraction = 0.1842f

// The product photo assets are square (1080x1080) — fitting them into Figma's portrait 260:320
// crop left empty letterbox space above the chair, pushing it down inside its own box instead of
// rising above the card as intended. A square box matching the real assets avoids that gap.
private const val PhotoAspectRatio = 1f

// Corner radius for the card's own top-left / top-right / bottom-left corners (Figma: 32).
private val CardCornerRadius = 32.dp

// The notch's two "step" corners (Figma: 26) and its final corner where it rejoins the card's
// bottom edge (Figma: 22) — traced directly from the "Card Background (Notched)" vector in the
// Figma file, not approximated as a circle.
private val NotchCornerRadiusLarge = 26.dp
private val NotchCornerRadiusSmall = 22.dp

// Fixed offsets (in dp, from the card's bottom-right corner) for each vertex of the notch cut,
// read straight off the Figma SVG path for "Card Background (Notched)" (a 303x382 box): the right
// edge runs straight down 85dp before curving in, steps left along a short ledge, curves again,
// drops straight down, then curves a final time to rejoin the bottom edge 136dp in from the right.
private val NotchTopFromBottom = 85.dp
private val NotchLedgeFromRight = 26.dp
private val NotchLedgeFromBottom = 59.dp
private val NotchLedgeEndFromRight = 88.dp
private val NotchStepFromRight = 114.dp
private val NotchStepFromBottom = 33.dp
private val NotchDropEndFromBottom = 22.dp
private val NotchBottomFromRight = 136.dp

// Matches the Cart pill's own rounding (RoundedCornerShape(50) on its ~53dp-tall body) so the
// gooey blob reads as a continuation of the button, not a mismatched shape.
private val CartPillCornerRadius = 26.dp

// Size of the product thumbnail that flies from the Cart button into the header's cart target.
private val CartTokenSize = 32.dp

// Cuts the exact stepped notch traced above out of the card's bottom-right corner, where the Cart
// pill sits flush, then draws the resulting shape — every seam is already tangent to the card's
// straight edges by construction, so no extra corner-rounding pass is needed.
private fun DrawScope.drawNotchedCard(color: Color) {
    val cornerPx = CardCornerRadius.toPx()
    val r1 = NotchCornerRadiusLarge.toPx()
    val r2 = NotchCornerRadiusSmall.toPx()

    val base = Path().apply {
        addRoundRect(RoundRect(rect = Rect(Offset.Zero, size), cornerRadius = CornerRadius(cornerPx)))
    }

    val xA = size.width
    val yA = size.height - NotchTopFromBottom.toPx()
    val xB = size.width - NotchLedgeFromRight.toPx()
    val yB = size.height - NotchLedgeFromBottom.toPx()
    val xC = size.width - NotchLedgeEndFromRight.toPx()
    val xD = size.width - NotchStepFromRight.toPx()
    val yD = size.height - NotchStepFromBottom.toPx()
    val yE = size.height - NotchDropEndFromBottom.toPx()
    val xF = size.width - NotchBottomFromRight.toPx()

    val notch = Path().apply {
        moveTo(xA, yA)
        arcTo(Rect(center = Offset(xB, yA), radius = r1), startAngleDegrees = 0f, sweepAngleDegrees = 90f, forceMoveTo = false)
        lineTo(xC, yB)
        arcTo(Rect(center = Offset(xC, yD), radius = r1), startAngleDegrees = 270f, sweepAngleDegrees = -90f, forceMoveTo = false)
        lineTo(xD, yE)
        arcTo(Rect(center = Offset(xF, yE), radius = r2), startAngleDegrees = 0f, sweepAngleDegrees = 90f, forceMoveTo = false)
        lineTo(size.width, size.height)
        close()
    }

    drawPath(Path().apply { op(base, notch, PathOperation.Difference) }, color)
}

@Composable
fun ProductCard(product: Product, transferState: GooeyTransferState, modifier: Modifier = Modifier) {
    BoxWithConstraints(modifier) {
        val imageOverflow = maxWidth * ImageOverflowFraction
        val cardImageSlotHeight = maxWidth - imageOverflow
        val cardColor = Color(product.colorHex)

        Column(
            Modifier
                .fillMaxWidth()
                .padding(top = imageOverflow)
                .drawBehind { drawNotchedCard(cardColor) },
        ) {
            Spacer(Modifier.height(cardImageSlotHeight))
            Column(Modifier.padding(16.dp)) {
                Text(
                    product.name,
                    color = Color.White,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
                Text(product.vendor, color = Color.White.copy(alpha = 0.8f), fontSize = 13.5.sp)
                Spacer(Modifier.height(8.dp))
                PriceText(product.price)
                Spacer(Modifier.height(16.dp))
            }
        }

        Image(
            painter = painterResource(id = product.imageRes),
            contentDescription = product.name,
            contentScale = ContentScale.Fit,
            modifier = Modifier
                .align(Alignment.TopCenter)
                .fillMaxWidth()
                .aspectRatio(PhotoAspectRatio),
        )

        // Flush with the card's bottom-right corner so it slices fully into the card, matching
        // the notch cut above. Tapping it transfers a thumbnail of the product into the header's
        // cart target via the Reactify gooey transfer effect.
        GooeySource(
            state = transferState,
            key = product.id,
            color = cardColor,
            cornerRadius = CartPillCornerRadius,
            token = {
                Image(
                    painter = painterResource(id = product.imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.size(CartTokenSize).clip(CircleShape),
                )
            },
            modifier = Modifier.align(Alignment.BottomEnd),
        ) {
            CartPill(iconModifier = Modifier.tokenAnchor())
        }
    }
}
