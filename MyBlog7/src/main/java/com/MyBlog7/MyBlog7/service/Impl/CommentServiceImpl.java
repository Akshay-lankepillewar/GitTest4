package com.MyBlog7.MyBlog7.service.Impl;

import com.MyBlog7.MyBlog7.entity.Comment;
import com.MyBlog7.MyBlog7.entity.Post;
import com.MyBlog7.MyBlog7.exception.ResourceNotFound;
import com.MyBlog7.MyBlog7.payload.CommentDto;
import com.MyBlog7.MyBlog7.repository.CommentRepository;
import com.MyBlog7.MyBlog7.repository.PostRepository;
import com.MyBlog7.MyBlog7.service.CommentService;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class CommentServiceImpl implements CommentService {
    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post not found with id:" + postId));

        List<Comment> comments = CommentRepository.findByPostId(postId);
        List<CommentDto> commentDtos = comments.stream().map(comment ->mapToDto(comment)).collect(Collectors.toList());
        return commentDtos;

    }
//@Override
//public List<CommentDto> getCommentsByPostId(long postId) {
//    Post post = postRepository.findById(postId).orElseThrow(
//            () -> new ResourceNotFound("Post not found with id:" + postId));
//
//    List<Comment> comments = CommentRepository.findByPostId(postId);
//    List<CommentDto> commentDtos = comments.stream()
//            .map(comment -> mapToDto(comment))
//            .collect(Collectors.toList());
//    return commentDtos;
//}

    private CommentRepository commentRepository;

    private PostRepository postRepository;
    private ModelMapper modelMapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper modelMapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = modelMapper;
    } 


    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFound("Post not found with id:" + postId)
        );
        comment.setPost(post);
        Comment saveComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(saveComment);
        return dto;
    }

    private CommentDto mapToDto(Comment saveComment) {
        CommentDto dto = modelMapper.map(saveComment, CommentDto.class);
        return dto;
    }

    private Comment mapToEntity(CommentDto commentDto) {
        Comment comment = modelMapper.map(commentDto, Comment.class);
        return comment;
    }


}
