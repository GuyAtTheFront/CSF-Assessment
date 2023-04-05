import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Review } from 'src/app/models/models';
import { MovieService } from 'src/app/services/movie.service';

@Component({
  selector: 'app-movie-reviews-list',
  templateUrl: './movie-reviews-list.component.html',
  styleUrls: ['./movie-reviews-list.component.css']
})
export class MovieReviewsListComponent implements OnInit{

  query!: string;
  initialReview: Review = { title: "", rating: "", byline: "",
    headline: "", summary: "", reviewURL: "", image: "", commentCount: 0 };

  reviews: Review[] = [this.initialReview];

  constructor( private route: ActivatedRoute,
              private movieService: MovieService ) {}

  ngOnInit(): void {
    this.query = this.route.snapshot.queryParams["movieName"]
    this.movieService.searchMovie(this.query).subscribe(x => this.reviews = x.reviews);
  }

}
