// Copyright 2021 The Bazel Authors. All rights reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//    http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.google.devtools.build.lib.runtime;

import com.google.auto.value.AutoValue;
import com.google.devtools.build.lib.concurrent.ThreadSafety.ThreadSafe;

/** A handler of memory pressure events. */
public interface MemoryPressureHandler {
  @ThreadSafe
  void handle(Event event);

  /** A memory pressure event. */
  @AutoValue
  abstract class Event {
    public abstract boolean wasManualGc();

    public abstract long tenuredSpaceUsedBytes();

    public abstract long tenuredSpaceMaxBytes();

    static Builder newBuilder() {
      return new AutoValue_MemoryPressureHandler_Event.Builder();
    }

    @AutoValue.Builder
    abstract static class Builder {
      abstract Builder setWasManualGc(boolean value);

      abstract Builder setTenuredSpaceUsedBytes(long value);

      abstract Builder setTenuredSpaceMaxBytes(long value);

      abstract Event build();
    }
  }
}
