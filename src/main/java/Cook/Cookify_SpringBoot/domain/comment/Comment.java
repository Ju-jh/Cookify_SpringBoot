package Cook.Cookify_SpringBoot.domain.comment;

import Cook.Cookify_SpringBoot.domain.BaseTimeEntity;
import Cook.Cookify_SpringBoot.domain.member.GoogleMember;
import Cook.Cookify_SpringBoot.domain.recipe.Recipe;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    private Long id;

    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id",nullable = false)
    private GoogleMember member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipe_id",nullable = false)
    private Recipe recipe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();


    //생성메서드
    public static Comment createComment(GoogleMember member, Recipe recipe, String content, Comment parent) {
        Comment comment = new Comment();
        comment.confirmMember(member);
        comment.confirmRecipe(recipe);
        comment.confirmParent(parent);
        comment.setContent(content);

        return comment;
    }

    //== 연관관계 편의 메서드 ==//
    public void confirmMember(GoogleMember member) {
        this.member = member;
        member.addComment(this);
    }

    public void confirmRecipe(Recipe recipe) {
        this.recipe = recipe;
        recipe.addComment(this);
    }

    public void confirmParent(Comment parent){
        this.parent = parent;
        parent.addChild(this);
    }

    public void addChild(Comment child){
        children.add(child);
    }

    public void updateContent(String content) {
        this.content = content;
    }




//    //== 비즈니스 로직 ==//
//    public List<Comment> findRemovableList() {
//
//        List<Comment> result = new ArrayList<>();
//
//        Optional.ofNullable(this.parent).ifPresentOrElse(
//
//                parentComment ->{//대댓글인 경우 (부모가 존재하는 경우)
//                    if( parentComment.isRemoved()&& parentComment.isAllChildRemoved()){
//                        result.addAll(parentComment.getChildren());
//                        result.add(parentComment);
//                    }
//                },
//
//                () -> {//댓글인 경우
//                    if (isAllChildRemoved()) {
//                        result.add(this);
//                        result.addAll(this.getChildren());
//                    }
//                }
//        );
//
//        return result;
//    }
//
//
//    //모든 자식 댓글이 삭제되었는지 판단
//    private boolean isAllChildRemoved() {
//        return getChildren().stream()//https://kim-jong-hyun.tistory.com/110 킹종현님 사랑합니다.
//                .map(Comment::isRemoved)//지워졌는지 여부로 바꾼다
//                .filter(isRemove -> !isRemove)//지워졌으면 true, 안지워졌으면 false이다. 따라서 filter에 걸러지는 것은 false인 녀석들이고, 있다면 false를 없다면 orElse를 통해 true를 반환한다.
//                .findAny()//지워지지 않은게 하나라도 있다면 false를 반환
//                .orElse(true);//모두 지워졌다면 true를 반환
//
//    }
}