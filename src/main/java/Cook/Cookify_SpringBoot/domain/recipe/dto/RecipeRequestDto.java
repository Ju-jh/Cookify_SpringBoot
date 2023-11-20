package Cook.Cookify_SpringBoot.domain.recipe.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class RecipeRequestDto {
    private String title;
    private String ingredients;
    private String ingredients2;
    private String steps;
    private String thumbnail;

    public RecipeRequestDto(String title, String ingredients, String ingredients2, String steps, String thumbnail) {
        this.title = title;
        this.ingredients = ingredients;
        this.ingredients2 = ingredients2;
        this.steps = steps;
        this.thumbnail = thumbnail;
    }
}
