package io.github.mulreon.cities.blocks;

import io.github.mulreon.cities.CitiesMod;
import io.github.mulreon.cities.items.ItemGroups;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.*;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.stream.Stream;

public class LaptopBlock extends Block {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty OPEN = BooleanProperty.of("open");

    public LaptopBlock(Settings settings) {
        super(settings);
    }


    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        builder.add(OPEN);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        player.playSound(SoundEvents.BLOCK_RESPAWN_ANCHOR_CHARGE, 1, 1);
        if (world.getBlockState(pos).get(OPEN)) {
            world.setBlockState(pos, state.with(OPEN, false));
        }
        else {
            world.setBlockState(pos, state.with(OPEN, true));
        }
        return ActionResult.SUCCESS;
    }

    public static final LaptopBlock LAPTOP_BLOCK = new LaptopBlock(FabricBlockSettings.of(Material.METAL).strength(0, 0).nonOpaque());
    public static void registry() {
        Registry.register(Registry.BLOCK, new Identifier(CitiesMod.MOD_ID, "laptop"), LAPTOP_BLOCK);
        Registry.register(Registry.ITEM, new Identifier(CitiesMod.MOD_ID, "laptop"), new BlockItem(LAPTOP_BLOCK, new FabricItemSettings().group(ItemGroups.DEVICE_GROUP)));
    }
}
