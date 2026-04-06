# Add project specific ProGuard rules here.

# Couchbase Lite
-keep class com.couchbase.lite.** { *; }

# Retrofit + Gson
-keepattributes Signature
-keepattributes *Annotation*
-keep class com.google.gson.** { *; }
-keep class retrofit2.** { *; }
-keepclassmembers,allowshrinking,allowobfuscation interface * {
    @retrofit2.http.* <methods>;
}

# DTO models (keep for Gson deserialization)
-keep class com.example.pokedexlite.data.remote.dto.** { *; }

# Hilt
-keep class dagger.hilt.** { *; }
-keep class javax.inject.** { *; }
