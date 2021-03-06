package com.github.ocraft.s2client.protocol.query;

/*-
 * #%L
 * ocraft-s2client-protocol
 * %%
 * Copyright (C) 2017 - 2018 Ocraft Project
 * %%
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * #L%
 */

import SC2APIProtocol.Query;
import com.github.ocraft.s2client.protocol.Strings;

import java.io.Serializable;
import java.util.Optional;

import static com.github.ocraft.s2client.protocol.Constants.nothing;
import static com.github.ocraft.s2client.protocol.DataExtractor.tryGet;
import static com.github.ocraft.s2client.protocol.Preconditions.require;

public final class Pathing implements Serializable {

    private static final long serialVersionUID = 2302282027991564714L;

    private final Float distance;

    private Pathing(Query.ResponseQueryPathing sc2ApiResponseQueryPathing) {
        distance = tryGet(Query.ResponseQueryPathing::getDistance, Query.ResponseQueryPathing::hasDistance)
                .apply(sc2ApiResponseQueryPathing).orElse(nothing());
    }

    public static Pathing from(Query.ResponseQueryPathing sc2ApiResponseQueryPathing) {
        require("sc2api response query pathing", sc2ApiResponseQueryPathing);
        return new Pathing(sc2ApiResponseQueryPathing);
    }

    public Optional<Float> getDistance() {
        return Optional.ofNullable(distance);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pathing pathing = (Pathing) o;

        return distance != null ? distance.equals(pathing.distance) : pathing.distance == null;
    }

    @Override
    public int hashCode() {
        return distance != null ? distance.hashCode() : 0;
    }

    @Override
    public String toString() {
        return Strings.toJson(this);
    }
}
