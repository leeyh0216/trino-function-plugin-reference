/*
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.leeyh0216.trino.udf.lambda;

import com.leeyh0216.trino.udf.SampleFunctionPlugin;
import io.trino.sql.query.QueryAssertions;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestSampleUnaryLambdaFunction
{
    private QueryAssertions assertions;

    @BeforeAll
    public void init()
    {
        assertions = new QueryAssertions();
        assertions.addPlugin(new SampleFunctionPlugin());
    }

    @AfterAll
    public void teardown()
    {
        assertions.close();
        assertions = null;
    }

    @Test
    public void test()
    {
        Assertions.assertThat(assertions.expression("sample_unary_lambda(input, x -> concat(cast(x as varchar), 'hello'))")
                        .binding("input", "ARRAY[10000000000, 20000000000, 30000000000]"))
                .matches("cast(ARRAY['10000000000hello', '20000000000hello', '30000000000hello'] AS array(varchar))");

        Assertions.assertThat(assertions.expression("sample_unary_lambda(input, x -> concat(cast(x as varchar), 'hello'))")
                        .binding("input", "ARRAY[1.1, 2.2, 3.3]"))
                .matches("cast(ARRAY['1.1hello', '2.2hello', '3.3hello'] AS array(varchar))");

        Assertions.assertThat(assertions.expression("sample_unary_lambda(input, x -> concat(cast(x as varchar), 'hello'))")
                        .binding("input", "ARRAY[true, false]"))
                .matches("cast(ARRAY['truehello', 'falsehello'] AS array(varchar))");

        Assertions.assertThat(assertions.expression("sample_unary_lambda(input, x -> concat(x, 'hello'))")
                        .binding("input", "ARRAY['a', 'b']"))
                .matches("cast(ARRAY['ahello', 'bhello'] AS array(varchar))");
    }
}
