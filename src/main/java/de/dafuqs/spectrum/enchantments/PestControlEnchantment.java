package de.dafuqs.spectrum.enchantments;

import de.dafuqs.spectrum.registries.SpectrumEnchantments;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;

public class PestControlEnchantment extends Enchantment {

    public PestControlEnchantment(Rarity weight, EquipmentSlot... slotTypes) {
        super(weight, EnchantmentTarget.DIGGER, slotTypes);
    }

    public int getMinPower(int level) {
        return 10;
    }

    public int getMaxPower(int level) {
        return super.getMinPower(level) + 20;
    }

    public int getMaxLevel() {
        return 1;
    }

    public boolean isTreasure() {
        return false;
    }

    public boolean isAvailableForEnchantedBookOffer() {
        return false;
    }

    public boolean isAvailableForRandomSelection() {
        return false;
    }

    public boolean canAccept(Enchantment other) {
        return super.canAccept(other) && other != SpectrumEnchantments.RESONANCE;
    }

}