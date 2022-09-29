# JPlay

## Maintenance

- [ ] **add code style guidelines to .editorconfig for the following languages**
    - [x] Java
    - [ ] html
    - [ ] css
    - [ ] Javascript

- [x] Add development branch.
- [ ] change application.properties to YAML format.
- [ ] Figure out more tasks

## Development

### Frontend:

### Backend:

- [ ] **Unit tests**
    + Well... At least some coverage.

---

- [ ] **Reading in music-files metadata and file path.**
    - [ ] Directory containing music files should be a configurable setting.
        - [ ] It should be possible to select a list of directories to scan for
          files.

    - [ ] Different file extensions should be accounted for (MP3, MPEG-1, WAV,
        AAC, Ogg Vorbis). Maybe some conversion to normalize files is needed.

---

- [ ] **Mapping file data to entities**
  - [ ] Create General POJO such as *Track*, *<del>Genre</del>*, *Playlist* etc.
  - [x] Create some parsers to Instantiate *Tracks*
  - [x] Create mappers to persist the entities in the database.

---

- [x] **Database**
  - [x] Design some ERD
  - [x] Create tables.
---

- [ ] **User sessions**
  + Users Can create playlists and add Tracks to them.
  - [ ] Database tables to persist users playlists.
  - [ ] 