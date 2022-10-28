package de.dafuqs.spectrum.recipe;

import de.dafuqs.revelationary.api.advancements.AdvancementHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.Recipe;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.Nullable;

public abstract class GatedSpectrumRecipe implements Recipe<Inventory>, GatedRecipe {
	
	public final Identifier id;
	public final String group;
	public final boolean secret;
	public final Identifier requiredAdvancementIdentifier;
	
	protected GatedSpectrumRecipe(Identifier id, String group, boolean secret, Identifier requiredAdvancementIdentifier) {
		this.id = id;
		this.group = group;
		this.secret = secret;
		this.requiredAdvancementIdentifier = requiredAdvancementIdentifier;
		
		registerInToastManager(getType(), this);
	}
	
	@Override
	public Identifier getId() {
		return this.id;
	}
	
	@Override
	public String getGroup() {
		return this.group;
	}
	
	@Override
	public boolean isSecret() {
		return this.secret;
	}
	
	/**
	 * The advancement the player has to have for the recipe be craftable
	 * @return The advancement identifier. A null value means the player is always able to craft this recipe
	 */
	@Nullable
	@Override
	public Identifier getRequiredAdvancementIdentifier() {
		return this.requiredAdvancementIdentifier;
	}
	
	public abstract Identifier getRecipeTypeUnlockIdentifier();
	
	@Override
	public boolean canPlayerCraft(PlayerEntity playerEntity) {
		return AdvancementHelper.hasAdvancement(playerEntity, getRecipeTypeUnlockIdentifier()) && AdvancementHelper.hasAdvancement(playerEntity, this.requiredAdvancementIdentifier);
	}
	
	public abstract String getRecipeTypeShortID();
	
	@Override
	public TranslatableText getSingleUnlockToastString() {
		return new TranslatableText("spectrum.toast." + getRecipeTypeShortID() + "_recipe_unlocked.title");
	}
	
	@Override
	public TranslatableText getMultipleUnlockToastString() {
		return new TranslatableText("spectrum.toast." + getRecipeTypeShortID() + "_recipes_unlocked.title");
	}
	
	@Override
	public boolean isIgnoredInRecipeBook() {
		return true;
	}
	
}
