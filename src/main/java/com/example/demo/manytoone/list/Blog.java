package com.example.demo.manytoone.list;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Comment;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 블로그[1] - 댓글[N]				<br>
 * 블로그에 여러댓글을 이력할 수 있다.	<br>
 * 댓글은 한번에 하나의 블로그에만 입력할 수 있다. 
 */
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
@Entity
@Table(name = "BLOG")
public class Blog  {
	
	@Id		
	@Column(name="BLOG_ID")
	String id;
	
	@Comment("블로그명")
	@Column(name="BLOG_NAME")
	String blogName;
		
	@OneToMany(mappedBy="team", cascade = CascadeType.ALL, orphanRemoval = true)
	List<BlogComment> members = new ArrayList<BlogComment>();			
	
	public Blog(String id, String blogName) {
		this.id = id;
		this.blogName = blogName;		
	}					
	
	public BlogComment getMember(String commntId) {
		BlogComment member = null;
		
		// java stream
		member = this.members.stream()
				             .filter(e -> e.memberId.equals(commntId))
							 .findFirst().orElse(null);
		
		return member;
	}
	
	public void joinMember(String memberId, String memberName) {
		this.getMembers().add(new BlogComment(this, memberId, memberName));
	}	
	
	public void leaveMember(BlogComment member) {
		this.getMembers().remove(member);
	}
	
	
}
