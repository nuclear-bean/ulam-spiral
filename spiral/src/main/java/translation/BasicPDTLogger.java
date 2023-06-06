package translation;

import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

@Log4j2
class BasicPDTLogger extends Thread {

    private final int matrixSize;
    private final int matrixElementsCount;
    private final PixelDataTranslator subject;

    public BasicPDTLogger(final int matrixSize, @NonNull final PixelDataTranslator subject) {
        this.matrixSize = matrixSize;
        this.subject = subject;
        this.matrixElementsCount = matrixSize * matrixSize;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            logProgress();
            try {
                //noinspection BusyWait
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
    }

    private void logProgress() {
        int processed = subject.i * matrixSize + subject.j;
        double progress = (double) processed / matrixElementsCount * 100;
        log.info(String.format("Progress: %.2f%%", progress));
    }
}
