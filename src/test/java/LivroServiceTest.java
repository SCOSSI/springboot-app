import br.com.springbootapp.model.entity.Livro;
import br.com.springbootapp.repository.LivroRepository;
import br.com.springbootapp.service.LivroService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import stub.LivroStub;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class LivroServiceTest {

    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    private Livro livroStub;

    @Before
    public void setup(){
        livroService = new LivroService(livroRepository);
        livroStub = LivroStub.buildLivro();
    }

    @Test
    public void testCreateLivro(){
        Mockito.when(livroRepository.create(Mockito.any(Livro.class))).thenReturn(LivroStub.buildLivro());

        Livro livro = livroService.createLivro(livroStub);

        Assert.assertEquals(livro.getAutor(), livroStub.getAutor());
        Assert.assertEquals(livro.getNome(), livroStub.getNome());
        Assert.assertEquals(livro.getId(), livroStub.getId());
    }

    @Test
    public void testFindAllLivros(){
        List<Livro> livrosFake = new ArrayList<>();
        livrosFake.add(livroStub);
        livrosFake.add(livroStub);

        Mockito.when(livroRepository.findAll()).thenReturn(livrosFake);

        List<Livro> livros = livroService.findAllLivros();
        Assert.assertEquals(livrosFake.size(), livros.size());
    }

    @Test
    public void testFindLivroById(){
        Mockito.when(livroRepository.findLivroById(1)).thenReturn(livroStub);
        Livro livro = livroService.findLivroById(1);

        Assert.assertEquals(livro.getAutor(), livroStub.getAutor());
        Assert.assertEquals(livro.getNome(), livroStub.getNome());
        Assert.assertEquals(livro.getId(), livroStub.getId());
    }

}
