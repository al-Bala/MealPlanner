package pl.mealplanner.recipe.domain;

class RecipeMapper {
    public static Recipe mapFromRecipeDtoToRecipe(RecipeDto recipeDto) {
        return Recipe.builder()
                .name(recipeDto.name())
                .isPublic(recipeDto.isPublic())
                .portions(recipeDto.portions())
                .prepareTime(recipeDto.prepareTime())
                .maxStorageTime(recipeDto.maxStorageTime())
                .diet(recipeDto.diet())
                .ingredients(recipeDto.ingredients())
                .steps(recipeDto.steps())
                .build();
    }

    public static RecipeDto mapFromRecipeToRecipeDto(Recipe recipe) {
        return RecipeDto.builder()
                .name(recipe.name())
                .isPublic(recipe.isPublic())
                .portions(recipe.portions())
                .prepareTime(recipe.prepareTime())
                .maxStorageTime(recipe.maxStorageTime())
                .diet(recipe.diet())
                .ingredients(recipe.ingredients())
                .steps(recipe.steps())
                .build();
    }
}
