const React = require("react");
const axios = require("axios").default;

module.exports = class TrackDetails extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            track: props.track
        }
    }

    static getDerivedStateFromProps(props, state) {
        return {track: props.track}
    }

    removeGenre(genre, event) {
        this.state.track.genres = this.state.track.genres
            .filter(g => g !== genre);
        this.setState(this.state)
        event.preventDefault()
    }

    addGenre(genre, event) {
        if (!genre || this.state.track.genres.includes(genre)) {
            return;
        }
        this.state.track.genres.push(genre)
        this.setState(this.state)
        event.preventDefault();
    }

    handleChange(event) {
        event.preventDefault();
        this.state.track[event.target.id] = event.target.value;
        this.setState(this.state)
    }

    handleSubmit(event) {
        event.preventDefault();
        axios.put("/update", this.state.track).then(response => {
            console.log(response)
        });
    }

    render() {
        if (!this.props.track) {
            return <h2>Select Track</h2>
        }

        const genres = this.state.track.genres.map(genre => {
            return <div title={genre} key={genre} className="genre">
                <input value={genre} id={genre} name={genre}
                       readOnly/>
                <button type="button"
                        onClick={event => this.removeGenre(genre, event)}>
                    -
                </button>
            </div>
        });
        // prevent null values
        const track = {
            id: this.state.track.id,
            filePath: this.state.track.filePath ? this.state.track.filePath : "",
            title: this.state.track.title ? this.state.track.title : "",
            author: this.state.track.author ? this.state.track.author : "",
            album: this.state.track.album ? this.state.track.album : "",
            genres: genres,
            comment: this.state.track.comment ? this.state.track.comment : "",
            durationSeconds: this.state.track.durationSeconds ? this.state.track.durationSeconds : 0,
            releaseDate: this.state.track.releaseDate ? this.state.track.releaseDate : ""
        }

        return (
            <div className="track-details">

                <div>
                    <b>Path: </b>
                    <span>{track.filePath}</span>
                </div>

                <div>
                    <form onSubmit={this.handleSubmit.bind(this)}>
                        <label htmlFor="title">Title:</label>
                        <input type="text" id="title" name="title"
                               onChange={event => this.handleChange(event)}
                               value={track.title}/><br/>

                        <label htmlFor="author">Author:</label>
                        <input type="text" id="author" name="author"
                               onChange={event => this.handleChange(event)}
                               value={track.author}/><br/>

                        <label htmlFor="genres">Genres:</label>
                        <fieldset className="genres">
                            {genres}
                            <br/>
                            <div className="new-genre">
                                <input type="text" id="new-genre"/>
                                <button type="button"
                                        onClick={
                                            event => this.addGenre(document.getElementById("new-genre").value, event)
                                        }>
                                    Add
                                </button>
                            </div>
                        </fieldset>
                        <label htmlFor="releaseDate">Release date:</label>
                        <input type="date" id="releaseDate" name="releaseDate"
                               onChange={event => this.handleChange(event)}
                               value={track.releaseDate}/><br/>

                        <label htmlFor="comment">Comment:</label>
                        <textarea style={{resize: "none"}}
                                  id="comment" name="comment" rows="10"
                                  onChange={event => this.handleChange(event)}
                                  value={track.comment}></textarea><br/>
                        <input type="submit" value="Update"></input>
                    </form>
                </div>
            </div>
        )
    }

}
