package model

data class Repository(
    val name: String,
    val owner: Owner,
    val description: String,
    val stargazerCount: Int,
    val primaryLanguage: Language?,
    val pushedAt: String,
    val createdAt: String,
    val updatedAt: String,
    val forkCount: Int,
    val isFork: Boolean,
    val watchers: Watchers,
    val issues: Issues,
    val pullRequests: PullRequests,
    val licenseInfo: LicenseInfo,
    val collaborators: Collaborators?,
    val languages: Languages
)

data class Owner(val login: String)

data class Language(val name: String)

data class Watchers(val totalCount: Int)

data class Issues(val totalCount: Int)

data class PullRequests(val totalCount: Int)

data class LicenseInfo(val name: String, val spdxId: String)

data class Collaborators(val totalCount: Int?) // Collaborators can be null

data class Languages(val nodes: List<Language>)
