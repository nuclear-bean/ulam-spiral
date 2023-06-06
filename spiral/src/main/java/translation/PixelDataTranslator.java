package translation;

import lombok.extern.log4j.Log4j2;
import translation.functions.TranslationFunction;
import visualtization.PixelData;

@Log4j2
public class PixelDataTranslator {

    private final long[][] matrix;
    private final TranslationFunction function;
    private final int matrixSize;
    private final BasicPDTLogger logger;

    int i;
    int j;

    public static PixelData[][] translate(long[][] matrix, TranslationFunction function) {
        log.info("Starting translation");
        PixelData[][] data = new PixelDataTranslator(matrix, function).doTranslate();
        log.info("Translation finished");
        return data;
    }

    private PixelDataTranslator(long[][] matrix, TranslationFunction function) {
        this.matrix = matrix;
        this.function = function;
        this.matrixSize = matrix.length;
        this.logger = new BasicPDTLogger(matrixSize, this);
    }

    private PixelData[][] doTranslate() {
        logger.start();
        PixelData[][] outputImageData = new PixelData[matrixSize][matrixSize];
        iterateOverMatrix(outputImageData);
        logger.interrupt();
        return outputImageData;
    }

    private void iterateOverMatrix(PixelData[][] outputImageData) {
        for (i = 0; i < matrixSize; i++) {
            for (j = 0; j < matrixSize; j++) {
                outputImageData[i][j] = function.calculatePixelValue(matrix, i, j);
            }
        }
    }
}
