/*
 * Copyright 2020 Parasoft Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.parasoft.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Repeatable;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * A WorkItem annotation allows you to associate test cases with development
 * artifacts defined in an external requirements management system.
 * <hr>
 * <blockquote> The annotation can be applied to either a test method or a
 * class.
 *
 * <pre>
 *    {@literal @}WorkItem(type=PR, id="123", url="http://www.parasoft.com")
 *    class MyTest {
 *        {@literal @}WorkItem(type=TASK, id="456")
 *        void testMethod() {
 *            assertTrue(true);
 *        }
 *    }
 * </pre>
 *
 * </blockquote>
 * <hr>
 * <blockquote> The annotation can be applied repeatedly.
 *
 * <pre>
 *    {@literal @}WorkItem(type=REQ, id="123")
 *    {@literal @}WorkItem(type=REQ, id="456")
 *    class MyTest {
 *        void testMethod() {
 *            assertTrue(true);
 *        }
 *    }
 * </pre>
 *
 * </blockquote>
 *
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
@Repeatable(WorkItem.List.class)
@Target({ ElementType.METHOD, ElementType.TYPE })
@Documented
@Inherited
public @interface WorkItem {

    /**
     * A specific type of development artifact in an external requirements
     * management system.
     */
    enum Type {

        /**
         * Tag to associate test case with a feature request in an external
         * requirements management system.
         */
        FR,

        /**
         * Tag to associate test case with a defect in an external requirements
         * management system.
         */
        PR,

        /**
         * Tag to associate test case with a requirement in an external
         * requirements management system.
         */
        REQ,

        /**
         * Tag to associate test case with a task in an external requirements
         * management system.
         */
        TASK,

        /**
         * Tag to associate test case with a test case specification in an
         * external requirements management system.
         */
        TEST;
    }

    /**
     * Type for the associated work item. Defaults to {@link Type#REQ} if not
     * specified.
     * @return An enum indicating the work item type
     */
    Type type() default Type.REQ;

    /**
     * Identifier for the associated work item.
     * @return A non-empty string representing the work item ID
     */
    String id();

    /**
     * Optional URL for the associated work item in an external requirements
     * management system.
     * @return A string URL to the external requirements management system or an
     * empty string if not specified
     */
    String url() default "";

    /**
     * Specifies multiple WorkItem annotations for the same test case.
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target({ ElementType.METHOD, ElementType.TYPE })
    @Documented
    @Inherited
    @interface List {
        /**
         * List of WorkItem annotations associated to current test case.
         * @return An array of all work item annotations at this target
         */
        WorkItem[] value();
    }
}

