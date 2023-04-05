import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserComment } from 'src/app/models/models';
import { MovieService } from 'src/app/services/movie.service';

import { firstValueFrom } from 'rxjs';

@Component({
  selector: 'app-post-comment',
  templateUrl: './post-comment.component.html',
  styleUrls: ['./post-comment.component.css']
})
export class PostCommentComponent implements OnInit {

commentForm!: FormGroup;
  movieName!: String;
  
  constructor( private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private movieService: MovieService ){};
              
              
  ngOnInit(): void {
    this.commentForm = this.createForm();
    this.movieName = this.route.snapshot.queryParams["movieName"];
  }

  createForm() {
    let group = this.fb.group({
      posterName: this.fb.control<string>("", [Validators.minLength(3), Validators.required]),
      rating: this.fb.control<number>(5, [ Validators.min(1), Validators.max(5), Validators.required]),
      comment:  this.fb.control<string>("", [ Validators.required])
    })
    return group;
  }

  async processForm() {
    console.log(this.commentForm.value);

    const comment = Object.assign({movieName: this.movieName}, this.commentForm.value) as UserComment;
    await firstValueFrom((this.movieService.postComment(comment))).then(x => {return})
    
    let query = sessionStorage.getItem("query");

    if(query){
      this.router.navigate(['/list'], {queryParams: {movieName: query}})
    } else {
      this.router.navigate(['/list'], {queryParams: {movieName: "notfound"}})
    }
  }

  onBackClicked() {
    let query = sessionStorage.getItem("query");

    if(query){
      this.router.navigate(['/list'], {queryParams: {movieName: query}})
    } else {
      this.router.navigate(['/list'], {queryParams: {movieName: "notfound"}})
    }
  }

}
