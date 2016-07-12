/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.test.fixtures.ivy

class IvyDescriptorDependency {
    String org
    String module
    String revision
    String conf
    String transitive
    Collection<IvyDescriptorDependencyExclusion> exclusions = []

    IvyDescriptorDependency hasConf(def conf) {
        assert this.conf == conf
        return this
    }
    boolean transitiveEnabled() { 
        return this.transitive != 'false'
    }
    boolean hasExcludes() {
        this.exclusions && !this.exclusions.isEmpty()
    }
    //For Legacy ivy publish via uploadAr
    boolean hasExclude(String org, String module, String name, String type, String ext, String conf, String matcher){
        if(hasExcludes()) {
            for(IvyDescriptorDependencyExclusion exclude : this.exclusions) {
                if (exclude.org == org && exclude.module == module && exclude.name == name && exclude.type == type &&  exclude.ext == ext && exclude.conf == conf && exclude.matcher == matcher) {
                    return true;
                }
           }
        }
        return false;
    }
}
