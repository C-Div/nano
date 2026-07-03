package cdiv.nano;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageType;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.world.World;

public class DamageSources {
    public static final RegistryKey<DamageType> STEPPING_DAMAGE = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Nano.id("stepping"));
    public static final RegistryKey<DamageType> OVEREATING = RegistryKey.of(RegistryKeys.DAMAGE_TYPE, Nano.id("overeating"));

    public static DamageSource getSteppingDamage(World world) {
        return get(world, STEPPING_DAMAGE);
    }

    public static DamageSource getSteppingDamage(World world, Entity attacker) {
        return get(world, STEPPING_DAMAGE, attacker);
    }

    public static DamageSource getOvereatingDamage(World world) {
        return get(world, OVEREATING);
    }

    public static DamageSource getOvereatingDamage(World world, Entity attacker) {
        return get(world, OVEREATING, attacker);
    }

    public static DamageSource get(World world, RegistryKey<DamageType> registryKey) {
        return new DamageSource(
            world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(registryKey)
        );
    }

    public static DamageSource get(World world, RegistryKey<DamageType> registryKey, Entity attacker) {
        return new DamageSource(
            world.getRegistryManager()
                .get(RegistryKeys.DAMAGE_TYPE)
                .entryOf(registryKey),
            attacker
        );
    }

    public static void initialize() {}
}
