package spiral.core.generator;

import lombok.experimental.UtilityClass;
import spiral.core.functions.RingFunctions;

@UtilityClass
public class RingGenerator {

    public long[] ringContent(int n) {
        int capacity = RingFunctions.calculateRingCapacity(n);
        long [] result = new long[capacity];
        result[0] = RingFunctions.calculateRingMinValue(n);
        for (int i = 1; i < result.length; i++) {
            result[i] = result[i-1] + 1;
        }
        return result;
    }
}
