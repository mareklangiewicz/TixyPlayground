
// region [[Andro App Build Imports and Plugs]]

import com.android.build.api.dsl.*
import org.jetbrains.kotlin.gradle.dsl.*
import org.jetbrains.kotlin.gradle.plugin.*
import com.vanniktech.maven.publish.*
import pl.mareklangiewicz.defaults.*
import pl.mareklangiewicz.deps.*
import pl.mareklangiewicz.utils.*
import pl.mareklangiewicz.templatefun.*

plugins {
  plugAll(
    plugs.TemplateFunNoVer, // version comes from the root: a versioned request here fails in composite builds
    plugs.AndroAppNoVer,
    plugs.KotlinMultiCompose,
    plugs.VannikPublish,
  )
}

// endregion [[Andro App Build Imports and Plugs]]

val lib = myLib(adjustInfo = { it.copy(namespace = "pl.mareklangiewicz.tixyplayground.androapp") })

defaultBuildTemplateForAndroApp(lib) {
  implementation(project(":common"))
}
