package cdiv.nano.integration;

import cdiv.nano.api.config.*;
import cdiv.nano.api.client.config.AppleSkin;
import cdiv.nano.api.client.config.FirstPersonModel;
import eu.midnightdust.lib.config.MidnightConfig;

@SuppressWarnings("DataFlowIssue")
public class MidnightLibIntegration extends MidnightConfig {
    public static final String FOOD = "food";
    public static final String ITEM = "item";
    public static final String LOOT = "loot";
    public static final String PATHFINDING = "pathfinding";
    public static final String SCALE_MODIFIERS = "scale_modifiers";
    public static final String SOUND = "sound";
    public static final String STEPPING = "stepping";
    public static final String XP = "xp";
    public static final String INTEGRATION = "integration";

    // Food Category
    @Entry(category = FOOD) public static boolean foodScalingEnabled = Food.foodScalingEnabled.defaultValue;
    @Entry(category = FOOD) public static boolean foodNutritionScalingEnabled = Food.foodNutritionScalingEnabled.defaultValue;
    @Entry(category = FOOD) public static boolean foodSaturationScalingEnabled = Food.foodSaturationScalingEnabled.defaultValue;
    @Entry(category = FOOD) public static boolean overeatingEnabled = Food.overeatingEnabled.defaultValue;
    @Entry(category = FOOD) public static double overeatingThreshold = Food.overeatingThreshold.defaultValue;

    // Item Category
    @Entry(category = ITEM) public static boolean itemScalingEnabled = Item.itemScalingEnabled.defaultValue;
    @Entry(category = ITEM) public static boolean itemDropScalingEnabled = Item.itemDropScalingEnabled.defaultValue;

    // Loot category
    @Entry(category = LOOT) public static boolean lootScalingEnabled = Loot.lootScalingEnabled.defaultValue;

    // Pathfinding category
    @Entry(category = PATHFINDING) public static boolean pathfindingScalingEnabled = Pathfinding.pathfindingScalingEnabled.defaultValue;
    @Entry(category = PATHFINDING) public static boolean pathfindingMinimumScalingEnabled = Pathfinding.pathfindingMinimumScalingEnabled.defaultValue;

    // Scale Modifiers category
    @Entry(category = SCALE_MODIFIERS) public static boolean scaleModifiersEnabled = ScaleModifiers.scaleModifiersEnabled.defaultValue;
    @Entry(category = SCALE_MODIFIERS) public static boolean knockbackScaleModifierEnabled = ScaleModifiers.knockbackScaleModifierEnabled.defaultValue;
    @Entry(category = SCALE_MODIFIERS) public static boolean heldItemScaleModifierEnabled = ScaleModifiers.heldItemScaleModifierEnabled.defaultValue;
    @Entry(category = SCALE_MODIFIERS) public static boolean dropsBaseScaleModifierEnabled = ScaleModifiers.dropsBaseScaleModifierEnabled.defaultValue;
    @Entry(category = SCALE_MODIFIERS) public static boolean attackDamageScaleModifierEnabled = ScaleModifiers.attackDamageScaleModifierEnabled.defaultValue;
    @Entry(category = SCALE_MODIFIERS) public static boolean attackSpeedScaleModifierEnabled = ScaleModifiers.attackSpeedScaleModifierEnabled.defaultValue;
    @Entry(category = SCALE_MODIFIERS) public static boolean miningSpeedScaleModifierEnabled = ScaleModifiers.miningSpeedScaleModifierEnabled.defaultValue;
    
    // Sound category
    @Entry(category = SOUND) public static boolean soundScalingEnabled = Sound.soundScalingEnabled.defaultValue;
    @Client @Entry(category = SOUND) public static boolean growthSoundsEnabled = cdiv.nano.api.client.config.Sound.growthSoundsEnabled.defaultValue;
    @Client @Entry(category = SOUND) public static boolean longGrowthSoundsEnabled = cdiv.nano.api.client.config.Sound.longGrowthSoundsEnabled.defaultValue;

    // Stepping category
    @Entry(category = STEPPING) public static boolean steppingDamageEnabled = Stepping.damageEnabled.defaultValue;
    @Entry(category = STEPPING) public static double steppingDamageRelativeHeightThreshold = Stepping.damageRelativeHeightThreshold.defaultValue;

    // Xp category
    @Entry(category = XP) public static boolean xpScalingEnabled = Xp.xpScalingEnabled.defaultValue;

    // Integration category
    @Condition(requiredModId = "appleskin")
    @Entry(category = INTEGRATION) public static boolean appleSkinIntegrationEnabled = AppleSkin.integrationEnabled.defaultValue;

    @Condition(requiredModId = "firstperson")
    @Entry(category = INTEGRATION) public static boolean firstPersonModelBodyOffsetScalingEnabled = FirstPersonModel.bodyOffsetScalingEnabled.defaultValue;
}
