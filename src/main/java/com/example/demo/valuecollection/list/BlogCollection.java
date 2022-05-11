package com.example.demo.valuecollection.list;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 값 타입 컬렉션의 제약사항												</br>
 	- 값 타입은 엔티티와 다르게 식별자 개념이 없다. 							</br>
 	  그래서 값을 변경하면 추적이 어렵다.									</br>	
 	- 값 타입 컬렉션에 변경이 일어나면 주인 엔티티와 연관된 모든 데이터를 삭제하고		</br>
	  값 타입 컬렉션에 있는 현재 값을 모두 다시 저장한다.						</br>
	- 값 타입 컬렉션을 매핑하는 테이블은 모든 컬럼을 묶어서 기본키를 구성해야 한다.	</br>
	  그렇게 할 경우 null 불가, 중복 저장 불가한 제약이 따른다.					</br>
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "BLOG_COLLECTION")
public class BlogCollection {

	@Id		
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ID")
	Long id;
	
	@Comment("블로그명")
	@Column(name="BLOG_NAME")	
	String blogName;
		
	@ElementCollection
    @CollectionTable(
        name = "BLOG_COLLECTION_COMMENT", 
        joinColumns = @JoinColumn(name = "BLOG_ID")
    )
	@OrderColumn(name = "COMMENT_SEQ")
	List<BlogCollectionComment> comments = new ArrayList<BlogCollectionComment>();			
	
	public BlogCollection(String blogName) {		
		this.blogName = blogName;		
	}					
	
	public BlogCollectionComment getComment(int index) {		
		return this.comments.get(index);
	}
	
	public void addComment(BlogCollectionComment comment) {
		this.comments.add(comment);
	}
	
	public void deleteComment(BlogCollectionComment comment) {
		this.comments.remove(comment);
	}
}
