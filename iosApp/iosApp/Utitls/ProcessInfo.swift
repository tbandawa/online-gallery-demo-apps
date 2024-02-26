//
//  ProcessInfo.swift
//  Online Gallery
//
//  Created by Tendai Bandawa on 2024/02/26.
//  Copyright © 2024 orgName. All rights reserved.
//

import Foundation

extension ProcessInfo {
    static var isPreview: Bool {
        return ProcessInfo.processInfo.environment["XCODE_RUNNING_FOR_PREVIEWS"] == "1"
    }
}
