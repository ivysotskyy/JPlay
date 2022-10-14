const React = require("react");

module.exports = class TrackDetails extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            track: props.track
        }
        console.log("ctor")
    }

    static getDerivedStateFromProps(props, state) {
        return {track: props.track}
    }

    removeGenre(genre) {
        this.state.track.genres = this.state.track.genres
            .filter(g => g !== genre);
        this.setState(this.state)
        console.log(this.state)
    }

    addGenre(genre) {
        this.state.track.genres.concat(genre)
        this.setState(this.state)
        console.log(this.state)
    }

    handleTitleChange(value, event) {
        this.setState({
            [event.target]: value
        })
    }

    render() {
        if (!this.props.track) {
            return <h2>Select Track</h2>
        }
        const genres = this.state.track.genres.map(genre => {
            return <div>
                <input value={genre} id={genre} name={genre}/>
                <button type="button"
                        onClick={event => this.removeGenre(genre)}>-
                </button>
            </div>
        })
        return (
            <div className="track-details">

                <div>
                    <b>Path: </b>
                    <span>{this.props.track.filePath}</span>
                </div>

                <div>
                    <form>
                        <label htmlFor="title">Title:</label>
                        <input type="text" id="title" name="title"
                               value={this.props.track.title}
                               onChange={event => this.handleTitleChange(this.value, event)}/><br/>

                        <label htmlFor="author">Author:</label>
                        <input type="text" id="author" name="author"
                               value={this.props.track.author}/><br/>

                        <label htmlFor="genres">Genres:</label>
                        <div className="genres">
                            {genres}
                            <br/><div>
                                <input type="text" id="new-genre"/>
                                <button type="button"
                                        onClick={
                                            event => this.addGenre(document.getElementById("new-genre").value)
                                        }>
                                    +
                                </button>
                            </div>
                        </div>
                        <label htmlFor="releaseDate">Release date:</label>
                        <input type="date" id="releaseDate" name="releaseDate"
                               value={this.props.track.releaseDate}/><br/>

                        <label htmlFor="comment">Comment:</label>
                        <textarea style={{resize: "none"}}
                            id="comment" name="comment" rows="10"
                                  value={this.props.track.comment}></textarea><br/>

                    </form>
                </div>
            </div>
        )
    }

}
