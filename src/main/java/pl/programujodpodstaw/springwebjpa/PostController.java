package pl.programujodpodstaw.springwebjpa;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService) {

        this.postService=postService;
    }

@GetMapping("posts")
    public ResponseEntity<Iterable<Post>> getAllPosts() {
    return postService.getAllPosts();
}

@GetMapping("posts/{id}")
    public ResponseEntity<Post> getPost(@PathVariable Integer id) {
        return postService.getPost(id);
    }

@PostMapping("posts")
    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        return postService.addPost(post);
}


@PutMapping("post/{id}")
public ResponseEntity<Post> editPost (@PathVariable Integer id, @RequestBody Post post){
        return postService.editPost(id, post);

}

@PatchMapping("post/{id}")
public ResponseEntity<Post> editLine (@PathVariable Integer id, @RequestBody Post post) {
        return postService.editLine(id,post);
}

@DeleteMapping("posts/{id}")
    public ResponseEntity<Post> deletePost (@PathVariable Integer id) {
   return postService.deletePost(id);
}

}
