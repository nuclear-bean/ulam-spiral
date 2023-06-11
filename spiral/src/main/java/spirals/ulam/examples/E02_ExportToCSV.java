package spirals.ulam.examples;

import export.csv.CSVExporter;
import lombok.extern.log4j.Log4j2;
import spirals.ulam.generators.SimpleUlamGenerator;
import translation.MatrixTranslator;
import translation.functions.Long2BooleanFunction;
import utils.ElapsedTimer;
import utils.export.OutputPathProvider;

import java.io.File;

/**
 * Creates basic Ulam spiral and saves it as csv. Output file is a matrix of given size filled with 0s and 1s where 1 represents prime number and 0 represents a composite number.
 */
@Log4j2
public class E02_ExportToCSV {

    private static final int SIZE = 1_001;

    public static void main(String[] args) {
        ElapsedTimer.start();
        long[][] matrix = generateBaseMatrix();
        boolean[][] booleanMapping = translateToBoolean(matrix);
        CSVExporter.generateCSV(booleanMapping, getOutputPath());
    }

    private static boolean[][] translateToBoolean(long[][] matrix) {
        log.info("Translating to boolean matrix ...");
        return MatrixTranslator.translate(matrix, Long2BooleanFunction.PRIME);
    }

    private static long[][] generateBaseMatrix() {
        log.info("Generating base matrix ...");
        return SimpleUlamGenerator.generateMatrix(SIZE);
    }

    private static File getOutputPath() {
        String path = OutputPathProvider.getOutputPath("ulam", SIZE, ".csv", E02_ExportToCSV.class);
        return new File(path);
    }
}
