/*
 * Copyright 2016-2018 47 Degrees, LLC. <http://www.47deg.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package github4s.free.adt

import cats.data.NonEmptyList
import github4s.GithubResponses.GHResponse
import github4s.free.domain._

/**
 * Repositories ops ADT
 */
sealed trait RepositoryOp[A]

final case class GetRepo(
    owner: String,
    repo: String,
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[Repository]]

final case class ListOrgRepos(
    org: String,
    `type`: Option[String] = None,
    pagination: Option[Pagination] = None,
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[List[Repository]]]

final case class GetContents(
    owner: String,
    repo: String,
    path: String,
    ref: Option[String] = None,
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[NonEmptyList[Content]]]

final case class ListCommits(
    owner: String,
    repo: String,
    sha: Option[String] = None,
    path: Option[String] = None,
    author: Option[String] = None,
    since: Option[String] = None,
    until: Option[String] = None,
    pagination: Option[Pagination] = None,
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[List[Commit]]]

final case class ListContributors(
    owner: String,
    repo: String,
    anon: Option[String] = None,
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[List[User]]]

final case class CreateRelease(
    owner: String,
    repo: String,
    tagName: String,
    name: String,
    body: String,
    targetCommitish: Option[String] = None,
    draft: Option[Boolean] = None,
    prerelease: Option[Boolean] = None,
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[Release]]

final case class GetCombinedStatus(
    owner: String,
    repo: String,
    ref: String,
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[CombinedStatus]]

final case class ListStatuses(
    owner: String,
    repo: String,
    ref: String,
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[List[Status]]]

final case class CreateStatus(
    owner: String,
    repo: String,
    sha: String,
    state: String,
    target_url: Option[String],
    description: Option[String],
    context: Option[String],
    accessToken: Option[String] = None
) extends RepositoryOp[GHResponse[Status]]