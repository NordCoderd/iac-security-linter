package dev.protsenko.securityLinter.docker.inspection.run.impl

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.psi.PsiElement
import dev.protsenko.securityLinter.core.HtmlProblemDescriptor
import dev.protsenko.securityLinter.core.SecurityPluginBundle
import dev.protsenko.securityLinter.docker.checker.UpdateWithoutInstallValidator
import dev.protsenko.securityLinter.docker.inspection.run.core.DockerfileRunAnalyzer

class PackageManagerUpdateWithoutInstallAnalyzer : DockerfileRunAnalyzer {
    override fun handle(runCommand: String, psiElement: PsiElement, holder: ProblemsHolder) {
        if (!UpdateWithoutInstallValidator.isValid(runCommand)) {
            val descriptor = HtmlProblemDescriptor(
                psiElement,
                SecurityPluginBundle.message("dfs021.documentation"),
                SecurityPluginBundle.message("dfs021.no-orphan-package-update"),
                ProblemHighlightType.WARNING
            )

            holder.registerProblem(descriptor)
        }
    }
}