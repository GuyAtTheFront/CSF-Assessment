export interface Review {

    title: string;
    rating: string;
    byline: string;
    headline: string;
    summary: string;
    reviewURL: string;
    image: string;
    commentCount: number;
}

export interface UserComment{
    movieName: string;
    posterName: string;
    comment: string;
    rating: string;
}
