package GraphQL

import com.apollographql.apollo3.ApolloClient
import model.*
import repository.FetchService
import src.main.GetRepositoryQuery
import src.main.SearchQuery
import java.util.stream.Collector

class ApolloRepoClient(
    private val apolloClient: ApolloClient
) : FetchService {
    override suspend fun getAllRepos(searchRepoName:String): List<Repository> {

      return  apolloClient
            .query(GetRepositoryQuery(searchRepoName))
            .execute()
            .data
            ?.search
            ?.edges
            ?.map { node -> toRepository(node)  }
            ?: emptyList<Repository>()

//        return listOf( Repository(
//            name = "schema_plus",
//            owner = Owner(login = "SchemaPlus"),
//            description = "SchemaPlus provides a collection of enhancements and extensions to ActiveRecord",
//            stargazerCount = 690,
//            primaryLanguage = Language(name = "Ruby"),
//            pushedAt = "2022-05-13T15:56:06Z",
//            createdAt = "2011-02-12T20:32:04Z",
//            updatedAt = "2023-02-02T09:21:51Z",
//            forkCount = 89,
//            isFork = false,
//            watchers = Watchers(totalCount = 22),
//            issues = Issues(totalCount = 166),
//            pullRequests = PullRequests(totalCount = 71),
//            licenseInfo = LicenseInfo(name = "Other", spdxId = "NOASSERTION"),
//            collaborators = null,
//            languages = Languages(nodes = listOf(Language(name = "Ruby")))
//        )

    }
    fun toRepository(edge:GetRepositoryQuery.Edge?):Repository{
        val repoDetail =  edge?.node?.onRepository
        return Repository(
            name = repoDetail?.name,
            owner = repoDetail?.owner?.login?.let { Owner(login = it) },
            description = repoDetail?.description,
            stargazerCount = repoDetail?.stargazerCount,
            primaryLanguage = repoDetail?.primaryLanguage?.let { Language(name = it.name) },
            pushedAt = repoDetail?.pushedAt.toString(),
            createdAt = repoDetail?.createdAt.toString(),
            updatedAt = repoDetail?.updatedAt.toString(),
            forkCount = repoDetail?.forkCount,
            isFork = false,
            watchers = repoDetail?.watchers?.totalCount?.let { Watchers(totalCount = it) },
            issues = repoDetail?.issues?.totalCount?.let { Issues(totalCount = it) },
            pullRequests = repoDetail?.pullRequests?.totalCount?.let { PullRequests(totalCount = it) },
            licenseInfo = repoDetail?.licenseInfo?.let { LicenseInfo(name = it.name, spdxId = it.spdxId) },
            collaborators =null,
            languages = null
        )
    }
}