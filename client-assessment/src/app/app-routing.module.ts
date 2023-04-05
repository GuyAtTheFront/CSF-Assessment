import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { SearchReviewComponent } from './view0/movie-search/search-review.component';
import { MovieReviewsListComponent } from './view1/movie-list/movie-reviews-list.component';
import { PostCommentComponent } from './view2/movie-comment/post-comment.component';

const routes: Routes = [
  {path: "", component: SearchReviewComponent},
  {path: "list", component: MovieReviewsListComponent},
  {path:"comment", component: PostCommentComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
