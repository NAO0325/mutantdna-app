package com.mutant.dna;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

// Test class added ONLY to cover main() invocation not covered by application tests.
@SpringBootTest
class MutantDnaApplicationTests {

    @Test
    void testMain() {
        MutantDnaApplication.main(new String[]{});
    }

}
