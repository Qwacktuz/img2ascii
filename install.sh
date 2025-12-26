#!/bin/sh

# Stop on error
set -e

echo "Attempting to create a directory at $HOME/.img2ascii-cli"
mkdir -p "$HOME"/.img2ascii-cli
echo "Success!"

echo "Copying fat JAR to $HOME/.img2ascii-cli"
if [ ! -f build/libs/img2ascii-all.jar ]; then
    echo "Error: JAR file not found. Run './gradlew shadowJar' first."
    exit 1
fi
cp build/libs/img2ascii-all.jar "$HOME"/.img2ascii-cli/img2ascii.jar
echo "Success!"

echo "Creating launch script at /usr/local/bin..."

sudo tee /usr/local/bin/img2ascii > /dev/null <<'EOF'
#!/bin/sh
java -jar "$HOME/.img2ascii-cli/img2ascii.jar" "$@"
EOF

echo "Success!"

echo "Making the file executable..."
sudo chmod +x /usr/local/bin/img2ascii

echo "Installation success! You can now run 'img2ascii <image>'"

