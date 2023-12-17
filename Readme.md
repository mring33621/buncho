# BUNCHO
1. The *Buncho* Java library currently provides very simple support for feature flags.
1. Just implement the 'FeatureFlags' interface and you're good to go.
1. It has 2 default methods that might do everything you need.
1. If not, then override them to do what you want.
1. There are 2 ways to set a feature flag: 
    1. Create a file named *$baseDir/$featureName_[true, false]*
    1. Set a boolean system property named *buncho.$featureName=[true, false]*
1. The default *baseDir* for files is ~/.buncho
1. The file approach takes precedence over the system property approach, as it is intended for emergency changes without restarting the JVM.
2. Examples:
    1. ~/.buncho/awesomeFeature_true
    1. -Dbuncho.awesomeFeature=true
1. I'm using Java 21, but *Buncho* will probably work with Java 8.

License: [Apache 2.0](https://www.apache.org/licenses/LICENSE-2.0.txt)
