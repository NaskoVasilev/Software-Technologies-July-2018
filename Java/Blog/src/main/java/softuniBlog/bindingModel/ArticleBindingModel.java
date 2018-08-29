package softuniBlog.bindingModel;

import javax.validation.constraints.NotNull;

public class ArticleBindingModel {
    @NotNull
    private String title;

    @NotNull
    private String content;

    @NotNull
    private String imageUrl;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
