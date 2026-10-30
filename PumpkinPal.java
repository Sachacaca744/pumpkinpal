package com.pumpkinpal;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SpawnEggItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

@Mod(PumpkinPal.MODID)
public class PumpkinPal {
    public static final String MODID = "pumpkinpal";

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(Registries.ENTITY_TYPE, MODID);
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(MODID);
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MODID);

    public static final DeferredHolder<EntityType<?>, EntityType<LivingPumpkin>> LIVING_PUMPKIN =
            ENTITIES.register("living_pumpkin", () -> EntityType.Builder.of(LivingPumpkin::new, MobCategory.CREATURE)
                    .sized(0.8f, 1.1f)
                    .clientTrackingRange(8)
                    .build(ResourceKey.create(Registries.ENTITY_TYPE,
                            ResourceLocation.fromNamespaceAndPath(MODID, "living_pumpkin"))));

    public static final DeferredItem<Item> LIVING_PUMPKIN_EGG =
            ITEMS.registerItem("living_pumpkin_spawn_egg", props -> new SpawnEggItem(LIVING_PUMPKIN.get(), props));

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> TAB =
            TABS.register("halloween", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup.pumpkinpal"))
                    .icon(() -> new ItemStack(LIVING_PUMPKIN_EGG.get()))
                    .displayItems((params, out) -> out.accept(LIVING_PUMPKIN_EGG.get()))
                    .build());

    public PumpkinPal(IEventBus modBus) {
        ENTITIES.register(modBus);
        ITEMS.register(modBus);
        TABS.register(modBus);
        modBus.addListener(this::attributes);
    }

    private void attributes(EntityAttributeCreationEvent event) {
        event.put(LIVING_PUMPKIN.get(), LivingPumpkin.createAttributes().build());
    }
}
