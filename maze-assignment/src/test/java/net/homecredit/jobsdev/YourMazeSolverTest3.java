package net.homecredit.jobsdev;

import net.homecredit.jobsdev.utils.Mazes;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Test instances are in zip file {@link YourMazeSolverTest3#TEST_DATA_ARCHIVE}. Each instance was randomly generated.
 * Instances of these types will play the most important factor in evaluation of your implementation.
 * Execution may take tens of seconds or minutes.
 * Feel free to add your own tests.
 *
 * @author embed.it
 */
@RunWith(Parameterized.class)
public class YourMazeSolverTest3 {

    private final String BASE_DIR = "src/test/resources";
    private final Path TEST_DATA_ARCHIVE = Paths.get(BASE_DIR, "test_instances.zip");

    private static final List<Object[]> BIG = Arrays.asList(new Object[][]{
        {"input_100.txt", true},
        {"input_100_nok.txt", false},
        {"input_150.txt", true},
        {"input_150_nok.txt", false},
        {"input_200.txt", true},
        {"input_200_nok.txt", false},
        {"input_500.txt", true},
        {"input_500_nok.txt", false},
        {"input_750.txt", true},
        {"input_750_nok.txt", false}
    });

    private static final List<Object[]> BIGGER = Arrays.asList(new Object[][]{
        {"input_1000.txt", true},
        {"input_1000_nok.txt", false},
        {"input_1500.txt", true},
        {"input_1500_nok.txt", false},
        {"input_2000.txt", true},
        {"input_2000_nok.txt", false},
        {"input_3000.txt", true},
        {"input_3000_nok.txt", false}
    });


    @Parameters(name = "test {index}: file {0}, paths exists {1}")
    public static Iterable<Object[]> data() {
        return new ArrayList<Object[]>() {{
            addAll(BIG);
            addAll(BIGGER);
        }};
    }

    @Parameter
    public String fileName;

    @Parameter(1)
    public boolean pathExistsExpected;

    private MazeSolver mazeSolver = new YourMazeSolver();


    @Ignore("Unignore when you are ready, this will take some time")
    @Test
    public void test() throws IOException {
        Path path = Mazes.getTestInstanceFile(TEST_DATA_ARCHIVE, fileName);
        boolean[][] maze = Mazes.readMaze(path);

        boolean exists = mazeSolver.existsPath(maze);
        assertEquals(pathExistsExpected, exists);
    }
}
