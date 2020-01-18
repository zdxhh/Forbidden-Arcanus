package com.stal111.forbidden_arcanus.entity.projectile;

import com.stal111.forbidden_arcanus.init.ModItems;
import com.stal111.forbidden_arcanus.init.ModEntities;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.IPacket;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.FMLPlayMessages;
import net.minecraftforge.fml.network.NetworkHooks;

public class BoomArrowEntity extends AbstractArrowEntity {

    public BoomArrowEntity(World world) {
        super((EntityType<? extends AbstractArrowEntity>) ModEntities.BOOM_ARROW.getEntityType(), world);
    }

    public BoomArrowEntity(double p_i48547_2_, double p_i48547_4_, double p_i48547_6_, World world) {
        super((EntityType<? extends AbstractArrowEntity>) ModEntities.BOOM_ARROW.getEntityType(), p_i48547_2_, p_i48547_4_, p_i48547_6_, world);
    }

    public BoomArrowEntity(LivingEntity entity, World world) {
        super((EntityType<? extends AbstractArrowEntity>) ModEntities.BOOM_ARROW.getEntityType(), entity, world);
    }

    public BoomArrowEntity(FMLPlayMessages.SpawnEntity spawnEntity, World world) {
        super((EntityType<? extends AbstractArrowEntity>) ModEntities.BOOM_ARROW.getEntityType(), world);
    }

    @Override
    protected ItemStack getArrowStack() {
        return ModItems.BOOM_ARROW.getStack();
    }

    @Override
    public IPacket<?> createSpawnPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    @Override
    public void tick() {
        Vec3d vec3d1 = this.getMotion();
        if (this.world.isRemote() && this.rand.nextDouble() >= 0.5) {
            this.world.addParticle(ParticleTypes.SMOKE, this.func_226277_ct_() - vec3d1.x, this.func_226278_cu_() - vec3d1.y + 0.05D, this.func_226281_cx_() - vec3d1.z, 0.0D, 0.0D, 0.0D);
        }
        super.tick();
    }

    @Override
    protected void arrowHit(LivingEntity entity) {
        this.world.createExplosion(this, entity.func_226277_ct_(), entity.func_226278_cu_(), entity.func_226281_cx_(), 3, Explosion.Mode.NONE);
        super.arrowHit(entity);
    }
}
