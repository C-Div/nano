package cdiv.nano.payload;

import cdiv.nano.Nano;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record IsMovingC2SPayload(boolean isMoving) implements CustomPayload {
    public static final CustomPayload.Id<IsMovingC2SPayload> ID = new CustomPayload.Id<>(Identifier.of(Nano.MOD_ID, "is_moving"));
    public static final PacketCodec<RegistryByteBuf, IsMovingC2SPayload> CODEC = PacketCodec.tuple(PacketCodecs.BOOL, IsMovingC2SPayload::isMoving, IsMovingC2SPayload::new);

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}