package com.MyBlog7.MyBlog7.controller;
import com.MyBlog7.MyBlog7.payload.PostDto;
import com.MyBlog7.MyBlog7.payload.PostResponse;
import com.MyBlog7.MyBlog7.service.PostService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
public class PostController {
    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }
    
    //http://localhost:8080/api/post
@PostMapping
    public ResponseEntity<?> savePost (@Valid @RequestBody PostDto postDto, BindingResult result) {
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getFieldError().getDefaultMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    PostDto dto = postService.savePost(postDto);
    return new ResponseEntity<>(dto, HttpStatus.CREATED);
}
@DeleteMapping("/{id}")
public ResponseEntity<String> deletePost(@PathVariable("id") long id){
 postService.deletePost(id);
 return new ResponseEntity<>("Post is deleted", HttpStatus.OK);

}
@PutMapping("/{id}")
public ResponseEntity<PostDto> updatePost(@PathVariable("id") long id, @RequestBody PostDto postDto){
    PostDto dto = postService.updatePost(id, postDto);
    return new ResponseEntity<>(dto,HttpStatus.OK);
}

@GetMapping("/{id}")
public ResponseEntity<PostDto > getPostById(@PathVariable("id") long id){
    PostDto dto = postService.getPostById(id);
    return new ResponseEntity<>(dto,HttpStatus.OK);
}

//http://localhost:8080/api/post?pageNo=0&pageSize=3&sortBy=title&sortDir=desc
@GetMapping
public PostResponse getPosts(

        @RequestParam(value = "pageNo", defaultValue = "0",required = false)int pageNo,
        @RequestParam(value = "PageSize",defaultValue = "5",required = false)int pageSize,
        @RequestParam(value = "sortBy",defaultValue = "id",required = false) String sortBy,
        @RequestParam(value = "sortDir",defaultValue = "asc", required = false) String sortDir
){
    PostResponse postResponse = postService.getPosts(pageNo,pageSize,sortBy,sortDir);
    return postResponse;

}


}
