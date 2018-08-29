package softuniBlog.bindingModel;

import javax.validation.constraints.NotNull;

public class CommentBindingModel {
    @NotNull
    private String content;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
