## Location Architecture

### Status

Database design phase.

---

### Context

CityStore is fundamentally a business discovery platform.

Customers must be able to:

- Discover nearby businesses
- Search businesses within a city
- Search businesses within a specific area
- Search businesses in cities other than their current location

The platform requires a scalable location model that supports all discovery use cases.

---

### Initial Assumption

A simple city-based model was initially considered.

Examples:

- Ahmedabad
- Mumbai
- Delhi

However, city-level information alone proved insufficient.

---

### Observation

Large cities contain many distinct areas.

Example:

Ahmedabad contains:

- Paldi
- Gita-Mandir
- Vastrapur
- CTM
- Gurukul Road

Customers searching Borivali expect different results than customers searching Dadar.

A city-only model cannot support precise local discovery.

---

### Additional Challenge

A customer's physical location may differ from their search location.

Example:

Current Location:
Mumbai

Search Location:
Ahmedabad

In this scenario, businesses should not be discovered relative to near by location, because it doesn't make sense.

This revealed an important distinction.

---

### Hierarchical Location Model

The platform will organize locations hierarchically.

Structure:

Country
→ State
→ City
→ Area

Example:

India
→ Gujarat
→ Ahmedabad
→ Paldi

India
→ Gujarat
→ Ahmedabad
→ Vastrapur

This hierarchy supports both city-level and area-level discovery.

---

### Geographic Coordinates

Businesses will store:

- Latitude
- Longitude

These coordinates enable:

- Nearby business search
- Distance calculations
- Radius filtering
- Future delivery zone support

Examples:

- Hospitals near me
- Restaurants within 3 km
- Clinics within a specific radius

---

### Search Behavior

#### City Search

Example:

Search Location:
Ahmedabad

Results:
All businesses within Ahmedabad.

---

#### Area Search

Example:

Search Location:
Paldi

Results:
Businesses located within Paldi.

---

#### GPS Search

When location access is granted:

- Current coordinates are obtained.
- Nearby businesses are calculated using distance formulas.

---

### User Location Storage

The platform will not continuously update a user's stored location whenever they move.

Instead:

- GPS location remains temporary runtime context.
- Search location drives business discovery.
- Optional preferred location may be stored for convenience.

This avoids unnecessary database updates.

---

### Proposed Core Entities

- Country
- State
- City
- Area
- Location

---

### Benefits

- Supports nearby discovery
- Supports city-wide search
- Supports area-level search
- Supports future expansion
- Supports GPS-based features
- Supports search independent of physical location

---

### Current Status

Future entity identification and database design will proceed using a hierarchical location architecture.