package GraphQL

import com.apollographql.apollo3.ApolloClient
import model.*
import repository.FetchService

class ApolloRepoClient(
    private val apolloClient: ApolloClient
) : FetchService {
    override suspend fun getAllRepos(): List<Repository> {
        return listOf( Repository(
            name = "schema_plus",
            owner = Owner(login = "SchemaPlus"),
            description = "SchemaPlus provides a collection of enhancements and extensions to ActiveRecord",
            stargazerCount = 690,
            primaryLanguage = Language(name = "Ruby"),
            pushedAt = "2022-05-13T15:56:06Z",
            createdAt = "2011-02-12T20:32:04Z",
            updatedAt = "2023-02-02T09:21:51Z",
            forkCount = 89,
            isFork = false,
            watchers = Watchers(totalCount = 22),
            issues = Issues(totalCount = 166),
            pullRequests = PullRequests(totalCount = 71),
            licenseInfo = LicenseInfo(name = "Other", spdxId = "NOASSERTION"),
            collaborators = null,
            languages = Languages(nodes = listOf(Language(name = "Ruby")))
        )
        )
    }
}