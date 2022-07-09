package io.github.mulreon.cities.mixin;

import io.github.mulreon.cities.CitiesMod;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameHud.class)
public class MoneyHudMixin {
    @Inject(at = @At("TAIL"), method = "render")
    public void render(MatrixStack matrixStack, float tickDelta, CallbackInfo info) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (!client.options.debugEnabled) {

            assert client.world != null;
            String displayString = "$10,000,000,000";
            float textPosX = 4;
            float textPosY = 4;

            double guiScale = client.getWindow().getScaleFactor();
            if (guiScale > 0) {
                textPosX /= guiScale;
                textPosY /= guiScale;
            }

            // Prevent FPS-Display to render outside screenspace
            float maxTextPosX = client.getWindow().getScaledWidth() - client.textRenderer.getWidth(displayString);
            float maxTextPosY = client.getWindow().getScaledHeight() - client.textRenderer.fontHeight;
            textPosX = Math.min(textPosX, maxTextPosX);
            textPosY = Math.min(textPosY, maxTextPosY);

            int textColor = ((230 & 0xFF) << 24) | 0xEEEEEE;

            this.renderText(matrixStack, client.textRenderer, displayString, textPosX, textPosY, textColor);
        }
    }

    private void renderText(MatrixStack matrixStack, TextRenderer textRenderer, String text, float x, float y, int color) {
        matrixStack.push();
        matrixStack.translate(x, y, 0);
        matrixStack.scale((float) 1, (float) 1, (float) 1);
        matrixStack.translate(-x, -y, 0);

        textRenderer.draw(matrixStack, text, x, y, color);
        matrixStack.pop();
    }
}
