package pl.programujodpodstaw.springwebjpa;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public ResponseEntity<Post> deletePost(@PathVariable Integer id) {
        if (!postRepository.existsById(id)) {
            return ResponseEntity.notFound().build();

        }
        postRepository.deleteById(id);
        return ResponseEntity.noContent().build();

    }

    public ResponseEntity<Iterable<Post>> getAllPosts() {
        Iterable<Post> posts = postRepository.findAll();

        return ResponseEntity.ok(posts);
    }

    public ResponseEntity<Post> getPost(@PathVariable Integer id) {
        return postRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());

    }

    public ResponseEntity<Post> addPost(@RequestBody Post post) {
        Post savedPost = postRepository.save(post);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedPost.getId())
                .toUri();

        return ResponseEntity.created(location).body(savedPost);
}
    public ResponseEntity<Post> editPost (@PathVariable Integer id, @RequestBody Post post) {
        return postRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setUser(post.getUser());
                    existingPost.setBody(post.getBody());

                    return postRepository.save(existingPost);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    public ResponseEntity<Post> editLine (@PathVariable Integer id, @RequestBody Post post) {
        return postRepository.findById(id)
                .map(existingPost -> {
                    if(post.getUser() != null) existingPost.setUser(post.getUser());
                    if(post.getBody() != null) existingPost.setBody(post.getBody());

                    return postRepository.save(existingPost);
                })
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
