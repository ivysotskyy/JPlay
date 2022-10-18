const React = require("react");
const {createRoot} = require("react-dom/client");
const axios = require("axios").default;
const TrackList = require("./TrackList");
const AudioPlayer = require("./AudioPlayer");
const TrackDetails = require("./TrackDetails");
const {BrowserRouter, Switch, Routes, Route, Link} = require("react-router-dom");

class App extends React.Component {

    constructor(props) {
        super(props);
        this.track = {
            author: "",
            title: "",
            filePath: "",
            fileName: "",
            duration: 0,
            releaseDate: "",
            comment: "",
            genres: []
        }
        this.state = {
            audioTracks: [],
            selectedSong: "",
            songPlaying: this.track
        };
    }

    onPlayAction(track) {
        this.setState({songPlaying: track})
    }

    onSelected(track) {
        this.setState({
            selectedSong: track
        });
    }

    componentDidMount() {
        //Get api routes
        axios.get("/tracks").then(response => {
            console.log(response)
            this.setState({
                audioTracks: response.data,
            });
        }).catch(err => {
            console.log(err)
        })
    }

    render() {
        /**
         * root: *The http-server hosting the music files.
         * because of chrome security reasons.
         */
        return (
            <div className="app">
                <header>
                    <h2> <a href="#"></a> </h2>
                </header>
                <div className={"wrapper"}>
                    <TrackList handleTrackSelected={this.onSelected.bind(this)} onPlayButton={this.onPlayAction.bind(this)} audioTracks={this.state.audioTracks}/>
                    <TrackDetails track={this.state.selectedSong}></TrackDetails>
                    <AudioPlayer root={"http://localhost:8081/"} song={this.state.songPlaying}></AudioPlayer>
                </div>
            </div>
        );
    }

}

createRoot(document.getElementById("react")).render(<App/>);