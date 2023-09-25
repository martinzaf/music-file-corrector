# Music File Corrector

An experiment in automating the reformatting of music filenames.
It presently assumes that the original filenames only contain a number
(corresponding to the track number) and the title itself.

Takes two arguments:

1. The filepath leading to the folder containing the music files
2. (optional). **-w**: changes each filename to the corrected name
in addition to writing the corrected names in an output document.

## Planned Features

- Different formatting parameters based on language
  - Example: in French, words in a song are all in minuscule save for the first word and any proper nouns
- Checking if 
