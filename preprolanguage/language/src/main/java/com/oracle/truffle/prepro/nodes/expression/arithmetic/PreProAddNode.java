/*
 * Copyright (c) 2012, 2018, Oracle and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * The Universal Permissive License (UPL), Version 1.0
 *
 * Subject to the condition set forth below, permission is hereby granted to any
 * person obtaining a copy of this software, associated documentation and/or
 * data (collectively the "Software"), free of charge and under any and all
 * copyright rights in the Software, and any and all patent rights owned or
 * freely licensable by each licensor hereunder covering either (i) the
 * unmodified Software as contributed to or provided by such licensor, or (ii)
 * the Larger Works (as defined below), to deal in both
 *
 * (a) the Software, and
 *
 * (b) any piece of software and/or hardware listed in the lrgrwrks.txt file if
 * one is included with the Software each a "Larger Work" to which the Software
 * is contributed by such licensors),
 *
 * without restriction, including without limitation the rights to copy, create
 * derivative works of, display, perform, and distribute the Software and make,
 * use, sell, offer for sale, import, export, have made, and have sold the
 * Software and the Larger Work(s), and to sublicense the foregoing rights on
 * either these or other terms.
 *
 * This license is subject to the following condition:
 *
 * The above copyright notice and either this complete permission notice or at a
 * minimum a reference to the UPL must be included in all copies or substantial
 * portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package com.oracle.truffle.prepro.nodes.expression.arithmetic;

import com.oracle.truffle.api.CompilerDirectives.TruffleBoundary;
import com.oracle.truffle.api.dsl.Fallback;
import com.oracle.truffle.api.dsl.Specialization;
import com.oracle.truffle.api.nodes.NodeInfo;
import com.oracle.truffle.prepro.PreProException;
import com.oracle.truffle.prepro.nodes.PreProBinaryNode;
import com.oracle.truffle.prepro.runtime.types.PreProConstant;
import com.oracle.truffle.prepro.runtime.types.PreProMatrix4;
import com.oracle.truffle.prepro.runtime.types.PreProScalar;
import com.oracle.truffle.prepro.runtime.types.PreProVector3;

/**
 * PrePro node that performs the "+" operation.
 * <p>
 * Type specialization on the input values is essential for the performance. This is achieved via
 * node rewriting: specialized subclasses handle just a single type, so that the generic node that
 * can handle all types is used only in cases where different types were encountered. The subclasses
 * are automatically generated by the Truffle DSL. In addition, a {@link PreProAddNodeGen factory class}
 * is generated that provides, e.g., {@link PreProAddNodeGen#create node creation}.
 */
@NodeInfo(shortName = "+")
public abstract class PreProAddNode extends PreProBinaryNode {

    /**
     * Specialization for {@code PreProConstant} values.
     * This specialization is automatically selected by the Truffle DSL if both the left and right
     * operand are {@code PreProConstant} values.
     */
    @Specialization
    @TruffleBoundary
    protected PreProConstant add(PreProConstant left, PreProConstant right) {
        return left.add(right);
    }

    /**
     * Specialization for the {@code PreProVector3} type.
     * <p>
     * This specialization is automatically selected by the Truffle DSL if both the left and right
     * operand are {@link PreProVector3} values.
     */
    @Specialization
    @TruffleBoundary
    protected PreProVector3 add(PreProVector3 left, PreProVector3 right) {
        return left.add(right);
    }

    /**
     * Specialization for the {@code PreProVector3} type.
     * <p>
     * This specialization is automatically selected by the Truffle DSL if the left operand
     * is a {@link PreProConstant} value and the right operand is a {@link PreProVector3} value.
     */
    @Specialization
    @TruffleBoundary
    protected PreProVector3 add(PreProConstant left, PreProVector3 right) {
        return left.add(right);
    }

    /**
     * Specialization for the {@code PreProVector3} type.
     * <p>
     * This specialization is automatically selected by the Truffle DSL if the left operand
     * is a {@link PreProVector3} value and the right operand is a {@link PreProConstant} value.
     */
    @Specialization
    @TruffleBoundary
    protected PreProVector3 add(PreProVector3 left, PreProConstant right) {
        return left.add(right);
    }

    /**
     * Specialization for {@code PreProScalar} values.
     * <p>
     * This specialization is automatically selected by the Truffle DSL if both the left and right
     * operand are {@link PreProScalar} values.
     */
    @Specialization
    @TruffleBoundary
    protected PreProScalar add(PreProScalar left, PreProScalar right) {
        return left.add(right);
    }

    /**
     * Specialization for {@code PreProMatrix4} values.
     * <p>
     * This specialization is automatically selected by the Truffle DSL if both the left and right
     * operand are {@link PreProMatrix4} values.
     */
    @Specialization
    @TruffleBoundary
    protected PreProMatrix4 add(PreProMatrix4 left, PreProMatrix4 right) {
        return left.add(right);
    }

    @Fallback
    protected Object typeError(Object left, Object right) {
        throw PreProException.typeError(this, left, right);
    }
}
