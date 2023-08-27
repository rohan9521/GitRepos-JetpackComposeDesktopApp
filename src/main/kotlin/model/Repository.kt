package model

import src.main.SearchQuery

data class Repository(
    val name: String? = "",
    val owner: Owner?=null,
    val description: String?=null,
    val stargazerCount: Int?=null,
    val primaryLanguage: Language?=null,
    val pushedAt: String?="",
    val createdAt: String?=null,
    val updatedAt: String?=null,
    val forkCount: Int?=null,
    val isFork: Boolean?=null,
    val watchers: Watchers?=null,
    val issues: Issues?=null,
    val pullRequests: PullRequests?=null,
    val licenseInfo: LicenseInfo?=null,
    val collaborators: Collaborators?=null,
    val languages: Languages?=null
)

data class Owner(val login: String)

data class Language(val name: String)

data class Watchers(val totalCount: Int)

data class Issues(val totalCount: Int)

data class PullRequests(val totalCount: Int)

data class LicenseInfo(val name: String, val spdxId: String)

data class Collaborators(val totalCount: Int?) // Collaborators can be null

data class Languages(val nodes: List<Language>)
